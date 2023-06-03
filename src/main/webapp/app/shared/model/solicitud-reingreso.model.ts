import dayjs from 'dayjs';
import { ICarrera } from 'app/shared/model/carrera.model';
import { IEstudiante } from 'app/shared/model/estudiante.model';
import { IDocumentoReingresoEstudiante } from 'app/shared/model/documento-reingreso-estudiante.model';

export interface ISolicitudReingreso {
  id?: number;
  fechaSolicitud?: string | null;
  motivo?: string | null;
  carreraSolicitada?: number | null;
  carrera?: ICarrera | null;
  estudiante?: IEstudiante | null;
  documentoReingresoEstudiantes?: IDocumentoReingresoEstudiante[] | null;
}

export const defaultValue: Readonly<ISolicitudReingreso> = {};
