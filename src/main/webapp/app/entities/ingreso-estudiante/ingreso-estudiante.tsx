import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IIngresoEstudiante } from 'app/shared/model/ingreso-estudiante.model';
import { getEntities } from './ingreso-estudiante.reducer';

export const IngresoEstudiante = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const ingresoEstudianteList = useAppSelector(state => state.ingresoEstudiante.entities);
  const loading = useAppSelector(state => state.ingresoEstudiante.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="ingreso-estudiante-heading" data-cy="IngresoEstudianteHeading">
        <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.home.title">Ingreso Estudiantes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/ingreso-estudiante/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.home.createLabel">Create new Ingreso Estudiante</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {ingresoEstudianteList && ingresoEstudianteList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.fechaIngreso">Fecha Ingreso</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.semestreInscripcion">Semestre Inscripcion</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.tipoIngreso">Tipo Ingreso</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.estudiante">Estudiante</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.carrera">Carrera</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.sede">Sede</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.pensum">Pensum</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ingresoEstudianteList.map((ingresoEstudiante, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/ingreso-estudiante/${ingresoEstudiante.id}`} color="link" size="sm">
                      {ingresoEstudiante.id}
                    </Button>
                  </td>
                  <td>
                    {ingresoEstudiante.fechaIngreso ? (
                      <TextFormat type="date" value={ingresoEstudiante.fechaIngreso} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{ingresoEstudiante.semestreInscripcion}</td>
                  <td>{ingresoEstudiante.tipoIngreso}</td>
                  <td>
                    {ingresoEstudiante.estudiante ? (
                      <Link to={`/estudiante/${ingresoEstudiante.estudiante.id}`}>{ingresoEstudiante.estudiante.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {ingresoEstudiante.carrera ? (
                      <Link to={`/carrera/${ingresoEstudiante.carrera.id}`}>{ingresoEstudiante.carrera.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {ingresoEstudiante.sede ? <Link to={`/sede/${ingresoEstudiante.sede.id}`}>{ingresoEstudiante.sede.id}</Link> : ''}
                  </td>
                  <td>
                    {ingresoEstudiante.pensum ? (
                      <Link to={`/pensum/${ingresoEstudiante.pensum.id}`}>{ingresoEstudiante.pensum.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/ingreso-estudiante/${ingresoEstudiante.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/ingreso-estudiante/${ingresoEstudiante.id}/edit`}
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
                        to={`/ingreso-estudiante/${ingresoEstudiante.id}/delete`}
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
              <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.home.notFound">No Ingreso Estudiantes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default IngresoEstudiante;
