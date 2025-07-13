'use client';
import dynamic from 'next/dynamic';
import { PositionEvent } from '@/hooks/useVehicleHistory';
import { LatLngTuple } from 'leaflet';
import 'leaflet/dist/leaflet.css';
import '@/lib/leaflet';      

const Map = dynamic(() => import('react-leaflet').then((m) => m.MapContainer), { ssr: false });
const TileLayer = dynamic(() => import('react-leaflet').then((m) => m.TileLayer), { ssr: false });
const Polyline = dynamic(() => import('react-leaflet').then((m) => m.Polyline), { ssr: false });
const Marker = dynamic(() => import('react-leaflet').then((m) => m.Marker), { ssr: false });
const Popup = dynamic(() => import('react-leaflet').then((m) => m.Popup), { ssr: false });

type Props = { events: PositionEvent[] };

export default function MapViewVehicle({ events }: Props) {
  if (!events.length) return <p>Nenhum evento nas últimas 24 h.</p>;

  const coords: LatLngTuple[] = events.map((e) => [e.lat, e.lng]);
  const last = coords[coords.length - 1];

  return (
    <Map center={last} zoom={8} className="h-[500px] w-full">
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
      <Polyline positions={coords} color="blue" />
      <Marker position={last}>
        <Popup>Posição atual</Popup>
      </Marker>
    </Map>
  );
}
