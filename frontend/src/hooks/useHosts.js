import { useState, useEffect, useCallback } from 'react';
import {
  getAllHosts,
  createHost,
  updateHost,
  deleteHost,
} from '../api/hostRepository';

export const useHosts = () => {
  const [hosts, setHosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchAll = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const { data } = await getAllHosts();
      setHosts(data);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to load hosts');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { fetchAll(); }, [fetchAll]);

  const create = useCallback(async (dto) => {
    await createHost(dto);
    await fetchAll();
  }, [fetchAll]);

  const update = useCallback(async (id, dto) => {
    await updateHost(id, dto);
    await fetchAll();
  }, [fetchAll]);

  const remove = useCallback(async (id) => {
    await deleteHost(id);
    await fetchAll();
  }, [fetchAll]);

  return { hosts, loading, error, refetch: fetchAll, create, update, remove };
};
