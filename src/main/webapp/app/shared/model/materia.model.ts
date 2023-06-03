import { IMateriasPensum } from 'app/shared/model/materias-pensum.model';

export interface IMateria {
  id?: number;
  nombreMateria?: string | null;
  creditos?: number | null;
  materiasPensums?: IMateriasPensum[] | null;
}

export const defaultValue: Readonly<IMateria> = {};
