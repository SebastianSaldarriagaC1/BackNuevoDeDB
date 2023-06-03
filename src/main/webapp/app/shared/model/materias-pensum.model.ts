import { IMateria } from 'app/shared/model/materia.model';
import { IPensum } from 'app/shared/model/pensum.model';

export interface IMateriasPensum {
  id?: number;
  materia?: IMateria | null;
  pensum?: IPensum | null;
}

export const defaultValue: Readonly<IMateriasPensum> = {};
