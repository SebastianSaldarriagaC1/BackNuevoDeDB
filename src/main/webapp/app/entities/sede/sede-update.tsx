import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ISede } from 'app/shared/model/sede.model';
import { getEntity, updateEntity, createEntity, reset } from './sede.reducer';

export const SedeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const sedeEntity = useAppSelector(state => state.sede.entity);
  const loading = useAppSelector(state => state.sede.loading);
  const updating = useAppSelector(state => state.sede.updating);
  const updateSuccess = useAppSelector(state => state.sede.updateSuccess);

  const handleClose = () => {
    navigate('/sede');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...sedeEntity,
      ...values,
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
          ...sedeEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="ingresosReingresosApp.sede.home.createOrEditLabel" data-cy="SedeCreateUpdateHeading">
            <Translate contentKey="ingresosReingresosApp.sede.home.createOrEditLabel">Create or edit a Sede</Translate>
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
                  id="sede-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('ingresosReingresosApp.sede.nombreSede')}
                id="sede-nombreSede"
                name="nombreSede"
                data-cy="nombreSede"
                type="text"
              />
              <ValidatedField
                label={translate('ingresosReingresosApp.sede.direccionSede')}
                id="sede-direccionSede"
                name="direccionSede"
                data-cy="direccionSede"
                type="text"
              />
              <ValidatedField
                label={translate('ingresosReingresosApp.sede.regional')}
                id="sede-regional"
                name="regional"
                data-cy="regional"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/sede" replace color="info">
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

export default SedeUpdate;
