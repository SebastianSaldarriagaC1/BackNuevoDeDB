import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IMateriasPensum } from 'app/shared/model/materias-pensum.model';
import { getEntities } from './materias-pensum.reducer';

export const MateriasPensum = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const materiasPensumList = useAppSelector(state => state.materiasPensum.entities);
  const loading = useAppSelector(state => state.materiasPensum.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="materias-pensum-heading" data-cy="MateriasPensumHeading">
        <Translate contentKey="ingresosReingresosApp.materiasPensum.home.title">Materias Pensums</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="ingresosReingresosApp.materiasPensum.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/materias-pensum/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="ingresosReingresosApp.materiasPensum.home.createLabel">Create new Materias Pensum</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {materiasPensumList && materiasPensumList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="ingresosReingresosApp.materiasPensum.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.materiasPensum.materia">Materia</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.materiasPensum.pensum">Pensum</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {materiasPensumList.map((materiasPensum, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/materias-pensum/${materiasPensum.id}`} color="link" size="sm">
                      {materiasPensum.id}
                    </Button>
                  </td>
                  <td>
                    {materiasPensum.materia ? <Link to={`/materia/${materiasPensum.materia.id}`}>{materiasPensum.materia.id}</Link> : ''}
                  </td>
                  <td>{materiasPensum.pensum ? <Link to={`/pensum/${materiasPensum.pensum.id}`}>{materiasPensum.pensum.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/materias-pensum/${materiasPensum.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/materias-pensum/${materiasPensum.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/materias-pensum/${materiasPensum.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="ingresosReingresosApp.materiasPensum.home.notFound">No Materias Pensums found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default MateriasPensum;
