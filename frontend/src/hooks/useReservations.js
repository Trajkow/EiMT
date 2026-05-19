import { useState, useEffect, useCallback } from 'react';
import {
    getAllReservations,
    createReservation,
} from '../api/reservationRepository';

export const useReservations = () => {
    const [reservations, setReservations] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchAll = useCallback(async () => {
        setLoading(true);
        setError(null);
        try {
            const { data } = await getAllReservations();
            setReservations(data);
        } catch (err) {
            setError(err.response?.data || err.response?.data?.message || 'Failed to load reservations');
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => { fetchAll(); }, [fetchAll]);

    const reserve = useCallback(async (accommodationId, dto) => {
        await createReservation(accommodationId, dto);
        await fetchAll();
    }, [fetchAll]);

    return { reservations, loading, error, refetch: fetchAll, reserve };
};
