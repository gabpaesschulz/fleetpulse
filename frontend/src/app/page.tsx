import MapView from '@/components/MapView';

export default function Home() {
  return (
    <main className="p-4">
      <h1 className="text-2xl font-bold mb-4">FleetPulse Dashboard</h1>
      <MapView />
    </main>
  );
}
