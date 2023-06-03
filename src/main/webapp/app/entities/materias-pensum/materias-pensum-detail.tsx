import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './materias-pensum.reducer';

export const MateriasPensumDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const materiasPensumEntity = useAppSelector(state => state.materiasPensum.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="materiasPensumDetailsHeading">
          <Translate contentKey="ingresosReingresosApp.materiasPensum.detail.title">MateriasPensum</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{materiasPensumEntity.id}</dd>
          <dt>
            <Translate contentKey="ingresosReingresosApp.materiasPensum.materia">Materia</Translate>
          </dt>
          <dd>{materiasPensumEntity.materia ? materiasPensumEntity.materia.id : ''}</dd>
          <dt>
            <Translate contentKey="ingresosReingresosApp.materiasPensum.pensum">Pensum</Translate>
          </dt>
          <dd>{materiasPensumEntity.pensum ? materiasPensumEntity.pensum.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/materias-pensum" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/materias-pensum/${materiasPensumEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MateriasPensumDetail;
