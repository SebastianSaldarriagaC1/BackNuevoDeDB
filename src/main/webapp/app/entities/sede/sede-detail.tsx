import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './sede.reducer';

export const SedeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const sedeEntity = useAppSelector(state => state.sede.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="sedeDetailsHeading">
          <Translate contentKey="ingresosReingresosApp.sede.detail.title">Sede</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{sedeEntity.id}</dd>
          <dt>
            <span id="nombreSede">
              <Translate contentKey="ingresosReingresosApp.sede.nombreSede">Nombre Sede</Translate>
            </span>
          </dt>
          <dd>{sedeEntity.nombreSede}</dd>
          <dt>
            <span id="direccionSede">
              <Translate contentKey="ingresosReingresosApp.sede.direccionSede">Direccion Sede</Translate>
            </span>
          </dt>
          <dd>{sedeEntity.direccionSede}</dd>
          <dt>
            <span id="regional">
              <Translate contentKey="ingresosReingresosApp.sede.regional">Regional</Translate>
            </span>
          </dt>
          <dd>{sedeEntity.regional}</dd>
        </dl>
        <Button tag={Link} to="/sede" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sede/${sedeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SedeDetail;
