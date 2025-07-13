'use client';
import dynamic from 'next/dynamic';
import 'leaflet/dist/leaflet.css';
import { useVehicles } from '@/hooks/useVehicles';
import Spinner from './Spinner'; 
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

import markerIcon2x from 'leaflet/dist/images/marker-icon-2x.png';
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerShadow from 'leaflet/dist/images/marker-shadow.png';

function url(x: any) {
  return typeof x === 'string' ? x : x.src;
}

delete (L.Icon.Default.prototype as any)._getIconUrl;

L.Icon.Default.mergeOptions({
  iconRetinaUrl: url(markerIcon2x),
  iconUrl:       url(markerIcon),
  shadowUrl:     url(markerShadow),
});


const Map = dynamic(
  () => import('react-leaflet').then((mod) => mod.MapContainer),
  { ssr: false }
);
const TileLayer = dynamic(
  () => import('react-leaflet').then((mod) => mod.TileLayer),
  { ssr: false }
);
const Marker = dynamic(
  () => import('react-leaflet').then((mod) => mod.Marker),
  { ssr: false }
);
const Popup = dynamic(
  () => import('react-leaflet').then((mod) => mod.Popup),
  { ssr: false }
);

export default function MapView() {
  const { data: vehicles, isLoading } = useVehicles();

  if (isLoading) return <Spinner />;

  return (
    <Map center={[-23.55, -46.63]} zoom={5} className="h-[500px] w-full">
      <TileLayer
        attribution='&copy; <a href="https://osm.org/copyright">OSM</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      {vehicles?.map(
        (v) =>
          v.lat &&
          v.lng && (
            <Marker key={v.id} position={[v.lat, v.lng]}>
              <Popup>
                <strong>{v.plate}</strong>
                <br /> Motorista: {v.driverName}
                <br /> Último sinal:{' '}
                {v.lastSeen ? new Date(v.lastSeen).toLocaleTimeString() : '—'}
              </Popup>
            </Marker>
          )
      )}
    </Map>
  );
}
