import dayjs from 'dayjs';
import { IIngresoEstudiante } from 'app/shared/model/ingreso-estudiante.model';
import { ISolicitudReingreso } from 'app/shared/model/solicitud-reingreso.model';

export interface IEstudiante {
  id?: number;
  nombre?: string | null;
  apellido?: string | null;
  fechaNacimiento?: string | null;
  correo?: string | null;
  direccion?: string | null;
  estado?: string | null;
  documento?: string | null;
  genero?: string | null;
  numeroContacto?: string | null;
  ingresoEstudiantes?: IIngresoEstudiante[] | null;
  solicitudReingresos?: ISolicitudReingreso[] | null;
}

export const defaultValue: Readonly<IEstudiante> = {};
