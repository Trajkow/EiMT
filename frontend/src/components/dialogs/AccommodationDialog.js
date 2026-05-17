import React, { useState, useEffect } from 'react';
import {
  Dialog, DialogTitle, DialogContent, DialogActions,
  Button, TextField, MenuItem, Grid, FormControlLabel, Switch,
} from '@mui/material';

const CATEGORIES = ['ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL'];

const EMPTY = { name: '', category: 'APARTMENT', HostId: '', numRooms: 1, isRented: false, rentCount: 0 };

const AccommodationDialog = ({ open, onClose, onSave, accommodation, hosts }) => {
  const isEdit = !!accommodation;
  const [form, setForm] = useState(EMPTY);
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    if (accommodation) {
      setForm({
        name: accommodation.name || '',
        category: accommodation.category || 'APARTMENT',
        HostId: accommodation.hostId || '',
        numRooms: accommodation.numRooms || 1,
        isRented: accommodation.isRented || false,
        rentCount: accommodation.rentCount || 0,
      });
    } else {
      setForm(EMPTY);
    }
  }, [accommodation, open]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) => ({ ...prev, [name]: type === 'checkbox' ? checked : value }));
  };

  const handleSave = async () => {
    setSaving(true);
    try {
      await onSave({
        ...form,
        HostId: Number(form.HostId),
        numRooms: Number(form.numRooms),
        rentCount: Number(form.rentCount),
      });
      onClose();
    } finally {
      setSaving(false);
    }
  };

  return (
    <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
      <DialogTitle sx={{ fontWeight: 700, pb: 1 }}>
        {isEdit ? 'Edit Accommodation' : 'Add New Accommodation'}
      </DialogTitle>
      <DialogContent sx={{ pt: 2 }}>
        <Grid container spacing={2} sx={{ mt: 0.5 }}>
          <Grid item xs={12}>
            <TextField
              fullWidth label="Name" name="name" value={form.name}
              onChange={handleChange} required size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth select label="Category" name="category"
              value={form.category} onChange={handleChange} size="small"
            >
              {CATEGORIES.map((c) => (
                <MenuItem key={c} value={c}>{c}</MenuItem>
              ))}
            </TextField>
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth select label="Host" name="HostId"
              value={form.HostId} onChange={handleChange} size="small" required
            >
              {(hosts || []).map((h) => (
                <MenuItem key={h.id} value={h.id}>
                  {h.name} {h.surname}
                </MenuItem>
              ))}
            </TextField>
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth label="Number of Rooms" name="numRooms" type="number"
              value={form.numRooms} onChange={handleChange} size="small"
              inputProps={{ min: 1 }}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth label="Rent Count" name="rentCount" type="number"
              value={form.rentCount} onChange={handleChange} size="small"
              inputProps={{ min: 0 }}
            />
          </Grid>
          <Grid item xs={12}>
            <FormControlLabel
              control={
                <Switch
                  checked={form.isRented}
                  onChange={handleChange}
                  name="isRented"
                  color="primary"
                />
              }
              label="Currently Rented"
            />
          </Grid>
        </Grid>
      </DialogContent>
      <DialogActions sx={{ px: 3, pb: 2 }}>
        <Button onClick={onClose} variant="outlined" disabled={saving}>Cancel</Button>
        <Button
          onClick={handleSave}
          variant="contained"
          disabled={saving || !form.name || !form.HostId}
          sx={{ background: '#e94560', '&:hover': { background: '#c73652' } }}
        >
          {saving ? 'Saving...' : isEdit ? 'Update' : 'Create'}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default AccommodationDialog;
