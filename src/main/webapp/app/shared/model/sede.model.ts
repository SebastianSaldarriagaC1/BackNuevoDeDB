import { IIngresoEstudiante } from 'app/shared/model/ingreso-estudiante.model';

export interface ISede {
  id?: number;
  nombreSede?: string | null;
  direccionSede?: string | null;
  regional?: string | null;
  ingresoEstudiantes?: IIngresoEstudiante[] | null;
}

export const defaultValue: Readonly<ISede> = {};
