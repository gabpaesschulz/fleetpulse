import L from 'leaflet';

import iconRetina from 'leaflet/dist/images/marker-icon-2x.png';
import icon from 'leaflet/dist/images/marker-icon.png';
import shadow from 'leaflet/dist/images/marker-shadow.png';

const url = (i: any) => (typeof i === 'string' ? i : i.src);

delete (L.Icon.Default.prototype as any)._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: url(iconRetina),
  iconUrl: url(icon),
  shadowUrl: url(shadow),
});
