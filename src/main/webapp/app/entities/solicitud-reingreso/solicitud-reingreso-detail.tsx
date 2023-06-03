import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './solicitud-reingreso.reducer';

export const SolicitudReingresoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const solicitudReingresoEntity = useAppSelector(state => state.solicitudReingreso.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="solicitudReingresoDetailsHeading">
          <Translate contentKey="ingresosReingresosApp.solicitudReingreso.detail.title">SolicitudReingreso</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{solicitudReingresoEntity.id}</dd>
          <dt>
            <span id="fechaSolicitud">
              <Translate contentKey="ingresosReingresosApp.solicitudReingreso.fechaSolicitud">Fecha Solicitud</Translate>
            </span>
          </dt>
          <dd>
            {solicitudReingresoEntity.fechaSolicitud ? (
              <TextFormat value={solicitudReingresoEntity.fechaSolicitud} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="motivo">
              <Translate contentKey="ingresosReingresosApp.solicitudReingreso.motivo">Motivo</Translate>
            </span>
          </dt>
          <dd>{solicitudReingresoEntity.motivo}</dd>
          <dt>
            <span id="carreraSolicitada">
              <Translate contentKey="ingresosReingresosApp.solicitudReingreso.carreraSolicitada">Carrera Solicitada</Translate>
            </span>
          </dt>
          <dd>{solicitudReingresoEntity.carreraSolicitada}</dd>
          <dt>
            <Translate contentKey="ingresosReingresosApp.solicitudReingreso.carrera">Carrera</Translate>
          </dt>
          <dd>{solicitudReingresoEntity.carrera ? solicitudReingresoEntity.carrera.id : ''}</dd>
          <dt>
            <Translate contentKey="ingresosReingresosApp.solicitudReingreso.estudiante">Estudiante</Translate>
          </dt>
          <dd>{solicitudReingresoEntity.estudiante ? solicitudReingresoEntity.estudiante.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/solicitud-reingreso" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/solicitud-reingreso/${solicitudReingresoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SolicitudReingresoDetail;
