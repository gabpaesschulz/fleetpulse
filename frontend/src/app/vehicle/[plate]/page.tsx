'use client';

import { useParams } from 'next/navigation';
import { useVehicleHistory } from '@/hooks/useVehicleHistory';
import Spinner from '@/components/Spinner';
import MapViewVehicle from '@/components/MapViewVehicle';

export default function VehiclePage() {
  const { plate } = useParams<{ plate: string }>();
  const { data, isLoading } = useVehicleHistory(plate);

  if (isLoading) return <Spinner />;

  return (
    <main className="p-4">
      <h1 className="text-xl font-bold mb-3">Veículo {plate}</h1>
      <MapViewVehicle events={data ?? []} />
    </main>
  );
}
