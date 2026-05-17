import React, { useState } from 'react';
import {
  Box, Typography, Button, Grid, Card, CardContent, CardActions,
  CircularProgress, Alert, Chip, Tooltip, IconButton,
} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import PublicIcon from '@mui/icons-material/Public';
import { useCountries } from '../hooks/useCountries';
import { useAuth } from '../context/AuthContext';
import CountryDialog from '../components/dialogs/CountryDialog';
import ConfirmDialog from '../components/shared/ConfirmDialog';

const CONTINENT_FLAGS = {
  'Europe': '🇪🇺',
  'Asia': '🌏',
  'Africa': '🌍',
  'North America': '🌎',
  'South America': '🌎',
  'Australia/Oceania': '🦘',
  'Antarctica': '🧊',
};

const CONTINENT_COLORS = {
  'Europe': '#0f3460',
  'Asia': '#e94560',
  'Africa': '#e67e00',
  'North America': '#2d8a4e',
  'South America': '#533483',
  'Australia/Oceania': '#1a6985',
  'Antarctica': '#666',
};

const CountriesPage = () => {
  const { countries, loading, error, create, update, remove } = useCountries();
  const { isAdmin } = useAuth();

  const [dialogOpen, setDialogOpen] = useState(false);
  const [editTarget, setEditTarget] = useState(null);
  const [deleteTarget, setDeleteTarget] = useState(null);
  const [actionError, setActionError] = useState('');

  const handleAdd = () => { setEditTarget(null); setDialogOpen(true); };
  const handleEdit = (country) => { setEditTarget(country); setDialogOpen(true); };
  const handleDelete = (country) => setDeleteTarget(country);

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

  // Group by continent
  const grouped = countries.reduce((acc, c) => {
    const cont = c.continent || 'Other';
    if (!acc[cont]) acc[cont] = [];
    acc[cont].push(c);
    return acc;
  }, {});

  return (
    <Box>
      {/* Page Header */}
      <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', mb: 4, flexWrap: 'wrap', gap: 2 }}>
        <Box>
          <Typography variant="h4" fontWeight={800} color="#1a1a2e" sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
            <PublicIcon sx={{ color: '#533483', fontSize: 36 }} />
            Countries
          </Typography>
          <Typography color="text.secondary" variant="body2" mt={0.5}>
            {countries.length} countries across {Object.keys(grouped).length} continents
          </Typography>
        </Box>
        {isAdmin && (
          <Button
            variant="contained"
            startIcon={<AddIcon />}
            onClick={handleAdd}
            sx={{
              background: 'linear-gradient(135deg, #533483, #0f3460)',
              fontWeight: 700,
              borderRadius: 3,
              textTransform: 'none',
              px: 3,
              '&:hover': { background: 'linear-gradient(135deg, #3d2460, #0a2440)' },
            }}
          >
            Add Country
          </Button>
        )}
      </Box>

      {actionError && <Alert severity="error" sx={{ mb: 2, borderRadius: 2 }} onClose={() => setActionError('')}>{actionError}</Alert>}

      {loading && (
        <Box sx={{ display: 'flex', justifyContent: 'center', py: 8 }}>
          <CircularProgress sx={{ color: '#533483' }} />
        </Box>
      )}

      {error && <Alert severity="error" sx={{ borderRadius: 2 }}>{error}</Alert>}

      {!loading && !error && (
        <Box>
          {Object.entries(grouped).sort().map(([continent, list]) => (
            <Box key={continent} sx={{ mb: 4 }}>
              <Box sx={{ display: 'flex', alignItems: 'center', gap: 1.5, mb: 2 }}>
                <Typography variant="h5" sx={{ fontSize: '1.5rem' }}>
                  {CONTINENT_FLAGS[continent] || '🌐'}
                </Typography>
                <Typography variant="h6" fontWeight={700} color={CONTINENT_COLORS[continent] || '#333'}>
                  {continent}
                </Typography>
                <Chip
                  label={list.length}
                  size="small"
                  sx={{
                    background: `${CONTINENT_COLORS[continent] || '#333'}18`,
                    color: CONTINENT_COLORS[continent] || '#333',
                    fontWeight: 700,
                  }}
                />
              </Box>
              <Grid container spacing={2}>
                {list.map((country) => (
                  <Grid item xs={12} sm={6} md={4} lg={3} key={country.id}>
                    <Card
                      elevation={0}
                      sx={{
                        borderRadius: 3,
                        border: `1px solid ${CONTINENT_COLORS[continent] || '#ccc'}22`,
                        transition: 'all 0.2s',
                        '&:hover': {
                          transform: 'translateY(-2px)',
                          boxShadow: `0 6px 20px ${CONTINENT_COLORS[continent] || '#ccc'}22`,
                          borderColor: `${CONTINENT_COLORS[continent] || '#ccc'}55`,
                        },
                      }}
                    >
                      <CardContent sx={{ pb: isAdmin ? 0 : 2 }}>
                        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1.5 }}>
                          <Box
                            sx={{
                              width: 36,
                              height: 36,
                              borderRadius: 2,
                              background: `${CONTINENT_COLORS[continent] || '#ccc'}18`,
                              display: 'flex',
                              alignItems: 'center',
                              justifyContent: 'center',
                              fontSize: '1.2rem',
                              flexShrink: 0,
                            }}
                          >
                            {CONTINENT_FLAGS[continent] || '🌐'}
                          </Box>
                          <Box sx={{ minWidth: 0 }}>
                            <Typography variant="subtitle1" fontWeight={700} color="#1a1a2e" noWrap>
                              {country.name}
                            </Typography>
                            <Typography variant="caption" color="text.secondary">
                              ID #{country.id}
                            </Typography>
                          </Box>
                        </Box>
                      </CardContent>

                      {isAdmin && (
                        <CardActions sx={{ px: 1.5, pb: 1.5, pt: 0, justifyContent: 'flex-end', gap: 0.5 }}>
                          <Tooltip title="Edit">
                            <IconButton
                              size="small"
                              onClick={() => handleEdit(country)}
                              sx={{ color: '#0f3460', '&:hover': { background: 'rgba(15,52,96,0.1)' } }}
                            >
                              <EditIcon fontSize="small" />
                            </IconButton>
                          </Tooltip>
                          <Tooltip title="Delete">
                            <IconButton
                              size="small"
                              onClick={() => handleDelete(country)}
                              sx={{ color: '#e94560', '&:hover': { background: 'rgba(233,69,96,0.1)' } }}
                            >
                              <DeleteIcon fontSize="small" />
                            </IconButton>
                          </Tooltip>
                        </CardActions>
                      )}
                    </Card>
                  </Grid>
                ))}
              </Grid>
            </Box>
          ))}

          {countries.length === 0 && (
            <Box sx={{ textAlign: 'center', py: 8, color: 'text.secondary' }}>
              <PublicIcon sx={{ fontSize: 64, opacity: 0.2, mb: 2 }} />
              <Typography>No countries found.</Typography>
            </Box>
          )}
        </Box>
      )}

      <CountryDialog
        open={dialogOpen}
        onClose={() => setDialogOpen(false)}
        onSave={handleSave}
        country={editTarget}
      />

      <ConfirmDialog
        open={!!deleteTarget}
        title="Delete Country"
        message={`Are you sure you want to delete "${deleteTarget?.name}"?`}
        onConfirm={handleConfirmDelete}
        onCancel={() => setDeleteTarget(null)}
      />
    </Box>
  );
};

export default CountriesPage;
