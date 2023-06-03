import { ISolicitudReingreso } from 'app/shared/model/solicitud-reingreso.model';
import { IIngresoEstudiante } from 'app/shared/model/ingreso-estudiante.model';

export interface ICarrera {
  id?: number;
  nombreCarrera?: string | null;
  modalidad?: string | null;
  facultad?: string | null;
  solicitudReingresos?: ISolicitudReingreso[] | null;
  ingresoEstudiantes?: IIngresoEstudiante[] | null;
}

export const defaultValue: Readonly<ICarrera> = {};
