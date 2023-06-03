import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import MateriasPensum from './materias-pensum';
import MateriasPensumDetail from './materias-pensum-detail';
import MateriasPensumUpdate from './materias-pensum-update';
import MateriasPensumDeleteDialog from './materias-pensum-delete-dialog';

const MateriasPensumRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<MateriasPensum />} />
    <Route path="new" element={<MateriasPensumUpdate />} />
    <Route path=":id">
      <Route index element={<MateriasPensumDetail />} />
      <Route path="edit" element={<MateriasPensumUpdate />} />
      <Route path="delete" element={<MateriasPensumDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default MateriasPensumRoutes;
