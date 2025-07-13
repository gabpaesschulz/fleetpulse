'use client';

import dynamic from 'next/dynamic';
import { useEffect } from 'react';
import 'leaflet/dist/leaflet.css';
import { useVehicles } from '@/hooks/useVehicles';
import Spinner from './Spinner';

const Map       = dynamic(() => import('react-leaflet').then(m => m.MapContainer), { ssr: false });
const TileLayer = dynamic(() => import('react-leaflet').then(m => m.TileLayer),    { ssr: false });
const Marker    = dynamic(() => import('react-leaflet').then(m => m.Marker),       { ssr: false });
const Popup     = dynamic(() => import('react-leaflet').then(m => m.Popup),        { ssr: false });

export default function MapView() {
  const { data: vehicles, isLoading } = useVehicles();

  useEffect(() => {
    (async () => {
      const L = await import('leaflet');

      const icon2x = (await import('leaflet/dist/images/marker-icon-2x.png')).default;
      const icon1x = (await import('leaflet/dist/images/marker-icon.png')).default;
      const shadow = (await import('leaflet/dist/images/marker-shadow.png')).default;

      const url = (i: unknown): string =>
        typeof i === 'string' ? i : (i as { src: string }).src;

      delete (
        L.Icon.Default.prototype as unknown as { _getIconUrl: unknown }
      )._getIconUrl;

      L.Icon.Default.mergeOptions({
        iconRetinaUrl: url(icon2x),
        iconUrl:       url(icon1x),
        shadowUrl:     url(shadow),
      });
    })();
  }, []);

  if (isLoading) return <Spinner />;

  return (
    <Map center={[-23.55, -46.63]} zoom={5} className="h-[500px] w-full">
      <TileLayer
        attribution='&copy; <a href="https://osm.org/copyright">OSM</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />

      {vehicles?.map(
        v =>
          v.lat &&
          v.lng && (
            <Marker key={v.id} position={[v.lat, v.lng]}>
              <Popup>
                <strong>{v.plate}</strong>
                <br />
                Motorista: {v.driverName}
                <br />
                Último sinal:{' '}
                {v.lastSeen ? new Date(v.lastSeen).toLocaleTimeString() : '—'}
              </Popup>
            </Marker>
          )
      )}
    </Map>
  );
}

