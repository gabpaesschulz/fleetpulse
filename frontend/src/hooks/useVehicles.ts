import { useQuery } from '@tanstack/react-query';
import { api } from '@/lib/api';

export type VehicleSummary = {
  id: string;
  plate: string;
  driverName: string;
  lat: number | null;
  lng: number | null;
  lastSeen: string | null;
};

export const useVehicles = () =>
  useQuery<VehicleSummary[]>({
    queryKey: ['vehicles'],
    queryFn: () => api.get('/vehicles').then((r) => r.data),
    refetchInterval: 10000, 
  });
