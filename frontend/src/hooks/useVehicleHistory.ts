import { useQuery } from '@tanstack/react-query';
import { api } from '@/lib/api';

export type PositionEvent = {
  plate: string;
  lat: number;
  lng: number;
  timestamp: string;
};

export const useVehicleHistory = (plate: string) =>
  useQuery<PositionEvent[]>({
    queryKey: ['history', plate],
    queryFn: () =>
      api
        .get(`/vehicles/${plate}`, {
          params: { since: new Date(Date.now() - 24 * 60 * 60 * 1000).toISOString() },
        })
        .then((r) => r.data),
  });
