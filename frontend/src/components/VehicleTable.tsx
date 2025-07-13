'use client';
import { VehicleSummary } from '@/hooks/useVehicles';
import clsx from 'clsx';

type Props = { vehicles: VehicleSummary[] };

function statusColor(last: string | null) {
  if (!last) return 'bg-red-500';
  const diff = Date.now() - new Date(last).getTime();
  if (diff < 2 * 60_000) return 'bg-green-500';
  if (diff < 5 * 60_000) return 'bg-yellow-400';
  return 'bg-red-500';
}

export default function VehicleTable({ vehicles }: Props) {
  return (
    <table className="min-w-full text-sm">
      <thead>
        <tr className="border-b">
          <th className="text-left py-1">Placa</th>
          <th className="text-left py-1">Motorista</th>
          <th className="text-left py-1">Último sinal</th>
          <th className="text-left py-1">Status</th>
        </tr>
      </thead>
      <tbody>
        {vehicles.map((v) => (
          <tr key={v.id} className="border-b">
            <td className="py-1">{v.plate}</td>
            <td className="py-1">{v.driverName}</td>
            <td className="py-1">
              {v.lastSeen ? new Date(v.lastSeen).toLocaleTimeString() : '—'}
            </td>
            <td className="py-1">
              <span
                className={clsx('inline-block w-3 h-3 rounded-full', statusColor(v.lastSeen))}
              />
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
