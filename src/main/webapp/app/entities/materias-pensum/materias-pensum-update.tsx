import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IMateria } from 'app/shared/model/materia.model';
import { getEntities as getMaterias } from 'app/entities/materia/materia.reducer';
import { IPensum } from 'app/shared/model/pensum.model';
import { getEntities as getPensums } from 'app/entities/pensum/pensum.reducer';
import { IMateriasPensum } from 'app/shared/model/materias-pensum.model';
import { getEntity, updateEntity, createEntity, reset } from './materias-pensum.reducer';

export const MateriasPensumUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const materias = useAppSelector(state => state.materia.entities);
  const pensums = useAppSelector(state => state.pensum.entities);
  const materiasPensumEntity = useAppSelector(state => state.materiasPensum.entity);
  const loading = useAppSelector(state => state.materiasPensum.loading);
  const updating = useAppSelector(state => state.materiasPensum.updating);
  const updateSuccess = useAppSelector(state => state.materiasPensum.updateSuccess);

  const handleClose = () => {
    navigate('/materias-pensum');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getMaterias({}));
    dispatch(getPensums({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...materiasPensumEntity,
      ...values,
      materia: materias.find(it => it.id.toString() === values.materia.toString()),
      pensum: pensums.find(it => it.id.toString() === values.pensum.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...materiasPensumEntity,
          materia: materiasPensumEntity?.materia?.id,
          pensum: materiasPensumEntity?.pensum?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="ingresosReingresosApp.materiasPensum.home.createOrEditLabel" data-cy="MateriasPensumCreateUpdateHeading">
            <Translate contentKey="ingresosReingresosApp.materiasPensum.home.createOrEditLabel">Create or edit a MateriasPensum</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="materias-pensum-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                id="materias-pensum-materia"
                name="materia"
                data-cy="materia"
                label={translate('ingresosReingresosApp.materiasPensum.materia')}
                type="select"
              >
                <option value="" key="0" />
                {materias
                  ? materias.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="materias-pensum-pensum"
                name="pensum"
                data-cy="pensum"
                label={translate('ingresosReingresosApp.materiasPensum.pensum')}
                type="select"
              >
                <option value="" key="0" />
                {pensums
                  ? pensums.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/materias-pensum" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default MateriasPensumUpdate;
