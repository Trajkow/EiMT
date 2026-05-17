import React, { useState } from 'react';
import {
  Box, Typography, Button, Grid, Card, CardContent, CardActions,
  CircularProgress, Alert, Avatar, Tooltip, IconButton,
} from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import PeopleIcon from '@mui/icons-material/People';
import { useHosts } from '../hooks/useHosts';
import { useCountries } from '../hooks/useCountries';
import { useAuth } from '../context/AuthContext';
import HostDialog from '../components/dialogs/HostDialog';
import ConfirmDialog from '../components/shared/ConfirmDialog';

const stringToColor = (str) => {
  let hash = 0;
  for (let i = 0; i < str.length; i++) {
    hash = str.charCodeAt(i) + ((hash << 5) - hash);
  }
  const h = hash % 360;
  return `hsl(${Math.abs(h)}, 55%, 40%)`;
};

const HostsPage = () => {
  const { hosts, loading, error, create, update, remove } = useHosts();
  const { countries } = useCountries();
  const { isAdmin } = useAuth();

  const [dialogOpen, setDialogOpen] = useState(false);
  const [editTarget, setEditTarget] = useState(null);
  const [deleteTarget, setDeleteTarget] = useState(null);
  const [actionError, setActionError] = useState('');

  const handleAdd = () => { setEditTarget(null); setDialogOpen(true); };
  const handleEdit = (host) => { setEditTarget(host); setDialogOpen(true); };
  const handleDelete = (host) => setDeleteTarget(host);

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

  const getCountryName = (countryId) => {
    const c = countries.find((c) => c.id === countryId);
    return c ? `${c.name}` : `Country #${countryId}`;
  };

  return (
    <Box>
      {/* Page Header */}
      <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', mb: 4, flexWrap: 'wrap', gap: 2 }}>
        <Box>
          <Typography variant="h4" fontWeight={800} color="#1a1a2e" sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
            <PeopleIcon sx={{ color: '#0f3460', fontSize: 36 }} />
            Hosts
          </Typography>
          <Typography color="text.secondary" variant="body2" mt={0.5}>
            {hosts.length} registered hosts
          </Typography>
        </Box>
        {isAdmin && (
          <Button
            variant="contained"
            startIcon={<AddIcon />}
            onClick={handleAdd}
            sx={{
              background: 'linear-gradient(135deg, #0f3460, #533483)',
              fontWeight: 700,
              borderRadius: 3,
              textTransform: 'none',
              px: 3,
              '&:hover': { background: 'linear-gradient(135deg, #0a2440, #3d2460)' },
            }}
          >
            Add Host
          </Button>
        )}
      </Box>

      {actionError && <Alert severity="error" sx={{ mb: 2, borderRadius: 2 }} onClose={() => setActionError('')}>{actionError}</Alert>}

      {loading && (
        <Box sx={{ display: 'flex', justifyContent: 'center', py: 8 }}>
          <CircularProgress sx={{ color: '#0f3460' }} />
        </Box>
      )}

      {error && <Alert severity="error" sx={{ borderRadius: 2 }}>{error}</Alert>}

      {!loading && !error && (
        <Grid container spacing={3}>
          {hosts.map((host) => {
            const initials = `${host.name?.[0] || ''}${host.surname?.[0] || ''}`.toUpperCase();
            const bgColor = stringToColor(`${host.name}${host.surname}`);
            return (
              <Grid item xs={12} sm={6} lg={4} key={host.id}>
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
                    <Box sx={{ display: 'flex', alignItems: 'center', gap: 2, mb: 2 }}>
                      <Avatar
                        sx={{
                          width: 52,
                          height: 52,
                          background: bgColor,
                          fontWeight: 700,
                          fontSize: '1.1rem',
                        }}
                      >
                        {initials}
                      </Avatar>
                      <Box>
                        <Typography variant="h6" fontWeight={700} color="#1a1a2e" sx={{ lineHeight: 1.2 }}>
                          {host.name} {host.surname}
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                          🌍 {getCountryName(host.countryId)}
                        </Typography>
                      </Box>
                    </Box>
                    <Typography variant="caption" color="text.disabled">
                      ID #{host.id}
                    </Typography>
                  </CardContent>

                  {isAdmin && (
                    <CardActions sx={{ px: 2, pb: 2, pt: 0, justifyContent: 'flex-end', gap: 0.5 }}>
                      <Tooltip title="Edit">
                        <IconButton
                          size="small"
                          onClick={() => handleEdit(host)}
                          sx={{ color: '#0f3460', '&:hover': { background: 'rgba(15,52,96,0.1)' } }}
                        >
                          <EditIcon fontSize="small" />
                        </IconButton>
                      </Tooltip>
                      <Tooltip title="Delete">
                        <IconButton
                          size="small"
                          onClick={() => handleDelete(host)}
                          sx={{ color: '#e94560', '&:hover': { background: 'rgba(233,69,96,0.1)' } }}
                        >
                          <DeleteIcon fontSize="small" />
                        </IconButton>
                      </Tooltip>
                    </CardActions>
                  )}
                </Card>
              </Grid>
            );
          })}

          {hosts.length === 0 && (
            <Grid item xs={12}>
              <Box sx={{ textAlign: 'center', py: 8, color: 'text.secondary' }}>
                <PeopleIcon sx={{ fontSize: 64, opacity: 0.2, mb: 2 }} />
                <Typography>No hosts found.</Typography>
              </Box>
            </Grid>
          )}
        </Grid>
      )}

      <HostDialog
        open={dialogOpen}
        onClose={() => setDialogOpen(false)}
        onSave={handleSave}
        host={editTarget}
        countries={countries}
      />

      <ConfirmDialog
        open={!!deleteTarget}
        title="Delete Host"
        message={`Are you sure you want to delete host "${deleteTarget?.name} ${deleteTarget?.surname}"?`}
        onConfirm={handleConfirmDelete}
        onCancel={() => setDeleteTarget(null)}
      />
    </Box>
  );
};

export default HostsPage;
