import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './carrera.reducer';

export const CarreraDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const carreraEntity = useAppSelector(state => state.carrera.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="carreraDetailsHeading">
          <Translate contentKey="ingresosReingresosApp.carrera.detail.title">Carrera</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{carreraEntity.id}</dd>
          <dt>
            <span id="nombreCarrera">
              <Translate contentKey="ingresosReingresosApp.carrera.nombreCarrera">Nombre Carrera</Translate>
            </span>
          </dt>
          <dd>{carreraEntity.nombreCarrera}</dd>
          <dt>
            <span id="modalidad">
              <Translate contentKey="ingresosReingresosApp.carrera.modalidad">Modalidad</Translate>
            </span>
          </dt>
          <dd>{carreraEntity.modalidad}</dd>
          <dt>
            <span id="facultad">
              <Translate contentKey="ingresosReingresosApp.carrera.facultad">Facultad</Translate>
            </span>
          </dt>
          <dd>{carreraEntity.facultad}</dd>
        </dl>
        <Button tag={Link} to="/carrera" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/carrera/${carreraEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CarreraDetail;
