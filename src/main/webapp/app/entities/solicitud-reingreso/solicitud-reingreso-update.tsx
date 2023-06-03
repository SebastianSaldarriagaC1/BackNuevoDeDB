import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICarrera } from 'app/shared/model/carrera.model';
import { getEntities as getCarreras } from 'app/entities/carrera/carrera.reducer';
import { IEstudiante } from 'app/shared/model/estudiante.model';
import { getEntities as getEstudiantes } from 'app/entities/estudiante/estudiante.reducer';
import { ISolicitudReingreso } from 'app/shared/model/solicitud-reingreso.model';
import { getEntity, updateEntity, createEntity, reset } from './solicitud-reingreso.reducer';

export const SolicitudReingresoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const carreras = useAppSelector(state => state.carrera.entities);
  const estudiantes = useAppSelector(state => state.estudiante.entities);
  const solicitudReingresoEntity = useAppSelector(state => state.solicitudReingreso.entity);
  const loading = useAppSelector(state => state.solicitudReingreso.loading);
  const updating = useAppSelector(state => state.solicitudReingreso.updating);
  const updateSuccess = useAppSelector(state => state.solicitudReingreso.updateSuccess);

  const handleClose = () => {
    navigate('/solicitud-reingreso');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCarreras({}));
    dispatch(getEstudiantes({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.fechaSolicitud = convertDateTimeToServer(values.fechaSolicitud);

    const entity = {
      ...solicitudReingresoEntity,
      ...values,
      carrera: carreras.find(it => it.id.toString() === values.carrera.toString()),
      estudiante: estudiantes.find(it => it.id.toString() === values.estudiante.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          fechaSolicitud: displayDefaultDateTime(),
        }
      : {
          ...solicitudReingresoEntity,
          fechaSolicitud: convertDateTimeFromServer(solicitudReingresoEntity.fechaSolicitud),
          carrera: solicitudReingresoEntity?.carrera?.id,
          estudiante: solicitudReingresoEntity?.estudiante?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="ingresosReingresosApp.solicitudReingreso.home.createOrEditLabel" data-cy="SolicitudReingresoCreateUpdateHeading">
            <Translate contentKey="ingresosReingresosApp.solicitudReingreso.home.createOrEditLabel">
              Create or edit a SolicitudReingreso
            </Translate>
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
                  id="solicitud-reingreso-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('ingresosReingresosApp.solicitudReingreso.fechaSolicitud')}
                id="solicitud-reingreso-fechaSolicitud"
                name="fechaSolicitud"
                data-cy="fechaSolicitud"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('ingresosReingresosApp.solicitudReingreso.motivo')}
                id="solicitud-reingreso-motivo"
                name="motivo"
                data-cy="motivo"
                type="text"
              />
              <ValidatedField
                label={translate('ingresosReingresosApp.solicitudReingreso.carreraSolicitada')}
                id="solicitud-reingreso-carreraSolicitada"
                name="carreraSolicitada"
                data-cy="carreraSolicitada"
                type="text"
              />
              <ValidatedField
                id="solicitud-reingreso-carrera"
                name="carrera"
                data-cy="carrera"
                label={translate('ingresosReingresosApp.solicitudReingreso.carrera')}
                type="select"
              >
                <option value="" key="0" />
                {carreras
                  ? carreras.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="solicitud-reingreso-estudiante"
                name="estudiante"
                data-cy="estudiante"
                label={translate('ingresosReingresosApp.solicitudReingreso.estudiante')}
                type="select"
              >
                <option value="" key="0" />
                {estudiantes
                  ? estudiantes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/solicitud-reingreso" replace color="info">
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

export default SolicitudReingresoUpdate;
