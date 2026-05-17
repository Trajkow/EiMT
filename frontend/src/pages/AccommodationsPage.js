import React, { useState } from 'react';
import {
  Box, Typography, Button, Grid, Card, CardContent, CardActions,
  Chip, CircularProgress, Alert, Tooltip, IconButton,
} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import ApartmentIcon from '@mui/icons-material/Apartment';
import HotelIcon from '@mui/icons-material/Hotel';
import { useAccommodations } from '../hooks/useAccommodations';
import { useHosts } from '../hooks/useHosts';
import { useAuth } from '../context/AuthContext';
import AccommodationDialog from '../components/dialogs/AccommodationDialog';
import ConfirmDialog from '../components/shared/ConfirmDialog';

const CATEGORY_COLORS = {
  ROOM: '#0f3460',
  HOUSE: '#533483',
  FLAT: '#e94560',
  APARTMENT: '#1a6985',
  HOTEL: '#2d8a4e',
  MOTEL: '#8a5200',
};

const AccommodationsPage = () => {
  const { accommodations, loading, error, create, update, remove } = useAccommodations();
  const { hosts } = useHosts();
  const { isAdmin } = useAuth();

  const [dialogOpen, setDialogOpen] = useState(false);
  const [editTarget, setEditTarget] = useState(null);
  const [deleteTarget, setDeleteTarget] = useState(null);
  const [actionError, setActionError] = useState('');

  const handleAdd = () => { setEditTarget(null); setDialogOpen(true); };
  const handleEdit = (acc) => { setEditTarget(acc); setDialogOpen(true); };
  const handleDelete = (acc) => setDeleteTarget(acc);

  const handleSave = async (dto) => {
    try {
      if (editTarget) {
        await update(editTarget.id, dto);
      } else {
        await create(dto);
      }
      setActionError('');
    } catch (e) {
      setActionError(e.response?.data?.message || 'Operation failed');
    }
  };

  const handleConfirmDelete = async () => {
    try {
      await remove(deleteTarget.id);
      setDeleteTarget(null);
      setActionError('');
    } catch (e) {
      setActionError(e.response?.data?.message || 'Delete failed');
      setDeleteTarget(null);
    }
  };

  const getHostName = (hostId) => {
    const h = hosts.find((h) => h.id === hostId);
    return h ? `${h.name} ${h.surname}` : `Host #${hostId}`;
  };

  return (
    <Box>
      {/* Page Header */}
      <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', mb: 4, flexWrap: 'wrap', gap: 2 }}>
        <Box>
          <Typography variant="h4" fontWeight={800} color="#1a1a2e" sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
            <ApartmentIcon sx={{ color: '#e94560', fontSize: 36 }} />
            Accommodations
          </Typography>
          <Typography color="text.secondary" variant="body2" mt={0.5}>
            {accommodations.length} total listings
          </Typography>
        </Box>
        {isAdmin && (
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
            Add Accommodation
          </Button>
        )}
      </Box>

      {actionError && <Alert severity="error" sx={{ mb: 2, borderRadius: 2 }} onClose={() => setActionError('')}>{actionError}</Alert>}

      {loading && (
        <Box sx={{ display: 'flex', justifyContent: 'center', py: 8 }}>
          <CircularProgress sx={{ color: '#e94560' }} />
        </Box>
      )}

      {error && <Alert severity="error" sx={{ borderRadius: 2 }}>{error}</Alert>}

      {!loading && !error && (
        <Grid container spacing={3}>
          {accommodations.map((acc) => (
            <Grid item xs={12} sm={6} lg={4} key={acc.id}>
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
                <CardContent sx={{ pb: 1 }}>
                  <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', mb: 2 }}>
                    <Box
                      sx={{
                        p: 1,
                        borderRadius: 2,
                        background: `${CATEGORY_COLORS[acc.category] || '#555'}22`,
                      }}
                    >
                      <HotelIcon sx={{ color: CATEGORY_COLORS[acc.category] || '#555' }} />
                    </Box>
                    <Chip
                      label={acc.isRented ? 'Rented' : 'Available'}
                      size="small"
                      sx={{
                        background: acc.isRented ? 'rgba(233,69,96,0.1)' : 'rgba(45,138,78,0.1)',
                        color: acc.isRented ? '#e94560' : '#2d8a4e',
                        fontWeight: 600,
                        border: `1px solid ${acc.isRented ? 'rgba(233,69,96,0.3)' : 'rgba(45,138,78,0.3)'}`,
                      }}
                    />
                  </Box>

                  <Typography variant="h6" fontWeight={700} color="#1a1a2e" gutterBottom noWrap>
                    {acc.name}
                  </Typography>

                  <Box sx={{ display: 'flex', gap: 1, flexWrap: 'wrap', mb: 2 }}>
                    <Chip
                      label={acc.category}
                      size="small"
                      sx={{
                        background: `${CATEGORY_COLORS[acc.category] || '#555'}18`,
                        color: CATEGORY_COLORS[acc.category] || '#555',
                        fontWeight: 600,
                        fontSize: '0.7rem',
                      }}
                    />
                  </Box>

                  <Box sx={{ display: 'flex', flexDirection: 'column', gap: 0.5 }}>
                    <Typography variant="body2" color="text.secondary">
                      🏠 <strong>{acc.numRooms}</strong> rooms
                    </Typography>
                    <Typography variant="body2" color="text.secondary" noWrap>
                      👤 {getHostName(acc.hostId)}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      📈 Rented <strong>{acc.rentCount || 0}</strong> times
                    </Typography>
                  </Box>
                </CardContent>

                {isAdmin && (
                  <CardActions sx={{ px: 2, pb: 2, pt: 0, justifyContent: 'flex-end', gap: 0.5 }}>
                    <Tooltip title="Edit">
                      <IconButton
                        size="small"
                        onClick={() => handleEdit(acc)}
                        sx={{
                          color: '#0f3460',
                          '&:hover': { background: 'rgba(15,52,96,0.1)' },
                        }}
                      >
                        <EditIcon fontSize="small" />
                      </IconButton>
                    </Tooltip>
                    <Tooltip title="Delete">
                      <IconButton
                        size="small"
                        onClick={() => handleDelete(acc)}
                        sx={{
                          color: '#e94560',
                          '&:hover': { background: 'rgba(233,69,96,0.1)' },
                        }}
                      >
                        <DeleteIcon fontSize="small" />
                      </IconButton>
                    </Tooltip>
                  </CardActions>
                )}
              </Card>
            </Grid>
          ))}

          {accommodations.length === 0 && (
            <Grid item xs={12}>
              <Box sx={{ textAlign: 'center', py: 8, color: 'text.secondary' }}>
                <ApartmentIcon sx={{ fontSize: 64, opacity: 0.2, mb: 2 }} />
                <Typography>No accommodations found.</Typography>
                {isAdmin && (
                  <Button onClick={handleAdd} sx={{ mt: 2 }} variant="outlined" startIcon={<AddIcon />}>
                    Add your first accommodation
                  </Button>
                )}
              </Box>
            </Grid>
          )}
        </Grid>
      )}

      <AccommodationDialog
        open={dialogOpen}
        onClose={() => setDialogOpen(false)}
        onSave={handleSave}
        accommodation={editTarget}
        hosts={hosts}
      />

      <ConfirmDialog
        open={!!deleteTarget}
        title="Delete Accommodation"
        message={`Are you sure you want to delete "${deleteTarget?.name}"?`}
        onConfirm={handleConfirmDelete}
        onCancel={() => setDeleteTarget(null)}
      />
    </Box>
  );
};

export default AccommodationsPage;
