import React, { useState } from 'react';
import {
    Box, Typography, Button, Grid, Card, CardContent,
    CircularProgress, Alert, Chip
} from '@mui/material';
import EventIcon from '@mui/icons-material/Event';
import AddIcon from '@mui/icons-material/Add';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import { useReservations } from '../hooks/useReservations';
import { useAccommodations } from '../hooks/useAccommodations';
import ReservationDialog from '../components/dialogs/ReservationDialog';

const ReservationsPage = () => {
    const { reservations, loading, error, reserve } = useReservations();
    const { accommodations } = useAccommodations();

    const [dialogOpen, setDialogOpen] = useState(false);
    const [actionError, setActionError] = useState('');

    const handleAdd = () => setDialogOpen(true);

    const handleSave = async (accommodationId, dto) => {
        try {
            await reserve(accommodationId, dto);
            setActionError('');
        } catch (e) {
            setActionError(e.response?.data || e.message || 'Operation failed');
            throw e; // throw so the dialog doesn't close on error
        }
    };

    const formatDate = (dateStr) => {
        if (!dateStr) return '';
        return new Date(dateStr).toLocaleString();
    };

    return (
        <Box>
            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', mb: 4, flexWrap: 'wrap', gap: 2 }}>
                <Box>
                    <Typography variant="h4" fontWeight={800} color="#1a1a2e" sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                        <EventIcon sx={{ color: '#e94560', fontSize: 36 }} />
                        Reservations
                    </Typography>
                    <Typography color="text.secondary" variant="body2" mt={0.5}>
                        {reservations.length} total reservations
                    </Typography>
                </Box>
                <Button
                    variant="contained"
                    startIcon={<AddIcon />}
                    onClick={handleAdd}
                    sx={{
                        background: 'linear-gradient(135deg, #e94560, #c73652)',
                        fontWeight: 700,
                        borderRadius: 3,
                        textTransform: 'none',
                        px: 3,
                        '&:hover': { background: 'linear-gradient(135deg, #c73652, #a52a40)' },
                    }}
                >
                    Add Reservation
                </Button>
            </Box>

            {actionError && <Alert severity="error" sx={{ mb: 2, borderRadius: 2 }} onClose={() => setActionError('')}>{actionError}</Alert>}

            {loading && (
                <Box sx={{ display: 'flex', justifyContent: 'center', py: 8 }}>
                    <CircularProgress sx={{ color: '#e94560' }} />
                </Box>
            )}

            {error && <Alert severity="error" sx={{ mb: 2, borderRadius: 2 }}>{error}</Alert>}

            {!loading && !error && (
                <Grid container spacing={3}>
                    {reservations.map((res) => (
                        <Grid item xs={12} sm={6} lg={4} key={res.id}>
                            <Card
                                elevation={0}
                                sx={{
                                    height: '100%',
                                    borderRadius: 3,
                                    border: '1px solid rgba(0,0,0,0.07)',
                                    transition: 'all 0.2s',
                                    '&:hover': {
                                        transform: 'translateY(-3px)',
                                        boxShadow: '0 8px 24px rgba(0,0,0,0.1)',
                                    },
                                }}
                            >
                                <CardContent sx={{ pb: 2 }}>
                                    <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', mb: 2 }}>
                                        <Box sx={{ p: 1, borderRadius: 2, background: 'rgba(233,69,96,0.1)' }}>
                                            <EventIcon sx={{ color: '#e94560' }} />
                                        </Box>
                                        <Chip
                                            icon={<CheckCircleIcon />}
                                            label="Confirmed"
                                            size="small"
                                            sx={{
                                                background: 'rgba(45,138,78,0.1)',
                                                color: '#2d8a4e',
                                                fontWeight: 600,
                                                border: '1px solid rgba(45,138,78,0.3)',
                                            }}
                                        />
                                    </Box>

                                    <Typography variant="h6" fontWeight={700} color="#1a1a2e" gutterBottom noWrap>
                                        {res.accommodationName}
                                    </Typography>

                                    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 1, mt: 1 }}>
                                        <Typography variant="body2" color="text.primary">
                                            👤 Reserved by: <strong>{res.username}</strong>
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            📅 From: {formatDate(res.reservedAt)}
                                        </Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            ⏳ To: {formatDate(res.releaseAt)}
                                        </Typography>
                                    </Box>
                                </CardContent>
                            </Card>
                        </Grid>
                    ))}

                    {reservations.length === 0 && (
                        <Grid item xs={12}>
                            <Box sx={{ textAlign: 'center', py: 8, color: 'text.secondary' }}>
                                <EventIcon sx={{ fontSize: 64, opacity: 0.2, mb: 2 }} />
                                <Typography>No reservations found.</Typography>
                            </Box>
                        </Grid>
                    )}
                </Grid>
            )}

            <ReservationDialog
                open={dialogOpen}
                onClose={() => setDialogOpen(false)}
                onSave={handleSave}
                accommodations={accommodations}
            />
        </Box>
    );
};

export default ReservationsPage;
