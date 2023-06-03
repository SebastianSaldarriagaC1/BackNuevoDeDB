import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPensum } from 'app/shared/model/pensum.model';
import { getEntities } from './pensum.reducer';

export const Pensum = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const pensumList = useAppSelector(state => state.pensum.entities);
  const loading = useAppSelector(state => state.pensum.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="pensum-heading" data-cy="PensumHeading">
        <Translate contentKey="ingresosReingresosApp.pensum.home.title">Pensums</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="ingresosReingresosApp.pensum.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/pensum/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="ingresosReingresosApp.pensum.home.createLabel">Create new Pensum</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {pensumList && pensumList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="ingresosReingresosApp.pensum.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="ingresosReingresosApp.pensum.numeroPensum">Numero Pensum</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pensumList.map((pensum, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/pensum/${pensum.id}`} color="link" size="sm">
                      {pensum.id}
                    </Button>
                  </td>
                  <td>{pensum.numeroPensum}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/pensum/${pensum.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/pensum/${pensum.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/pensum/${pensum.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="ingresosReingresosApp.pensum.home.notFound">No Pensums found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Pensum;
