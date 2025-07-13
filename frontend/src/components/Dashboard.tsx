'use client';

import MapView from '@/components/MapView';
import { useVehicles } from '@/hooks/useVehicles';
import VehicleTable from '@/components/VehicleTable';
import { useEventStream } from '@/hooks/useEventStream';

export default function Dashboard() {
  const { data: vehicles = [], isLoading } = useVehicles();
  useEventStream();

  return (
    <div className="grid lg:grid-cols-2 gap-4">
      <MapView />
      <div className="border rounded-lg p-3 h-[500px] overflow-y-auto">
        <h2 className="font-semibold mb-2">Veículos</h2>
        {isLoading ? <p>Carregando…</p> : <VehicleTable vehicles={vehicles} />}
      </div>
    </div>
  );
}
