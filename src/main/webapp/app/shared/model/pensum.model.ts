import { IMateriasPensum } from 'app/shared/model/materias-pensum.model';
import { IIngresoEstudiante } from 'app/shared/model/ingreso-estudiante.model';

export interface IPensum {
  id?: number;
  numeroPensum?: number | null;
  materiasPensums?: IMateriasPensum[] | null;
  ingresoEstudiantes?: IIngresoEstudiante[] | null;
}

export const defaultValue: Readonly<IPensum> = {};
