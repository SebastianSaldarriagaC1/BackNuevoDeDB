import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './ingreso-estudiante.reducer';

export const IngresoEstudianteDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const ingresoEstudianteEntity = useAppSelector(state => state.ingresoEstudiante.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="ingresoEstudianteDetailsHeading">
          <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.detail.title">IngresoEstudiante</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{ingresoEstudianteEntity.id}</dd>
          <dt>
            <span id="fechaIngreso">
              <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.fechaIngreso">Fecha Ingreso</Translate>
            </span>
          </dt>
          <dd>
            {ingresoEstudianteEntity.fechaIngreso ? (
              <TextFormat value={ingresoEstudianteEntity.fechaIngreso} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="semestreInscripcion">
              <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.semestreInscripcion">Semestre Inscripcion</Translate>
            </span>
          </dt>
          <dd>{ingresoEstudianteEntity.semestreInscripcion}</dd>
          <dt>
            <span id="tipoIngreso">
              <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.tipoIngreso">Tipo Ingreso</Translate>
            </span>
          </dt>
          <dd>{ingresoEstudianteEntity.tipoIngreso}</dd>
          <dt>
            <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.estudiante">Estudiante</Translate>
          </dt>
          <dd>{ingresoEstudianteEntity.estudiante ? ingresoEstudianteEntity.estudiante.id : ''}</dd>
          <dt>
            <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.carrera">Carrera</Translate>
          </dt>
          <dd>{ingresoEstudianteEntity.carrera ? ingresoEstudianteEntity.carrera.id : ''}</dd>
          <dt>
            <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.sede">Sede</Translate>
          </dt>
          <dd>{ingresoEstudianteEntity.sede ? ingresoEstudianteEntity.sede.id : ''}</dd>
          <dt>
            <Translate contentKey="ingresosReingresosApp.ingresoEstudiante.pensum">Pensum</Translate>
          </dt>
          <dd>{ingresoEstudianteEntity.pensum ? ingresoEstudianteEntity.pensum.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/ingreso-estudiante" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ingreso-estudiante/${ingresoEstudianteEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default IngresoEstudianteDetail;
