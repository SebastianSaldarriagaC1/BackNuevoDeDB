import dayjs from 'dayjs';
import { IEstudiante } from 'app/shared/model/estudiante.model';
import { ICarrera } from 'app/shared/model/carrera.model';
import { ISede } from 'app/shared/model/sede.model';
import { IPensum } from 'app/shared/model/pensum.model';
import { IDocumentoIngresoEstudiante } from 'app/shared/model/documento-ingreso-estudiante.model';

export interface IIngresoEstudiante {
  id?: number;
  fechaIngreso?: string | null;
  semestreInscripcion?: string | null;
  tipoIngreso?: string | null;
  estudiante?: IEstudiante | null;
  carrera?: ICarrera | null;
  sede?: ISede | null;
  pensum?: IPensum | null;
  documentoIngresoEstudiantes?: IDocumentoIngresoEstudiante[] | null;
}

export const defaultValue: Readonly<IIngresoEstudiante> = {};
