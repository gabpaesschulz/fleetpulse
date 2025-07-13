import { useEffect } from 'react';
import { VehicleSummary } from './useVehicles';
import { queryClient } from '@/lib/queryClient';

export function useEventStream() {
  useEffect(() => {
    const es = new EventSource(`${process.env.NEXT_PUBLIC_API_URL}/stream`);

    es.onmessage = (e) => {
      const ev = JSON.parse(e.data) as {
        plate: string;
        lat: number;
        lng: number;
        timestamp: string;
      };

      queryClient.setQueryData<VehicleSummary[]>(['vehicles'], (old) => {
        if (!old) return old;
        return old.map((v) =>
          v.plate === ev.plate
            ? { ...v, lat: ev.lat, lng: ev.lng, lastSeen: ev.timestamp }
            : v
        );
      });
    };

    return () => es.close();
  }, []);
}
