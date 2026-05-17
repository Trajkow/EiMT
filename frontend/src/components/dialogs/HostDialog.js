import React, { useState, useEffect } from 'react';
import {
  Dialog, DialogTitle, DialogContent, DialogActions,
  Button, TextField, MenuItem, Grid,
} from '@mui/material';

const EMPTY = { name: '', surname: '', countryID: '' };

const HostDialog = ({ open, onClose, onSave, host, countries }) => {
  const isEdit = !!host;
  const [form, setForm] = useState(EMPTY);
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    if (host) {
      setForm({
        name: host.name || '',
        surname: host.surname || '',
        countryID: host.countryId || '',
      });
    } else {
      setForm(EMPTY);
    }
  }, [host, open]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    setSaving(true);
    try {
      await onSave({ ...form, countryID: Number(form.countryID) });
      onClose();
    } finally {
      setSaving(false);
    }
  };

  return (
    <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
      <DialogTitle sx={{ fontWeight: 700, pb: 1 }}>
        {isEdit ? 'Edit Host' : 'Add New Host'}
      </DialogTitle>
      <DialogContent sx={{ pt: 2 }}>
        <Grid container spacing={2} sx={{ mt: 0.5 }}>
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth label="First Name" name="name" value={form.name}
              onChange={handleChange} required size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth label="Last Name" name="surname" value={form.surname}
              onChange={handleChange} required size="small"
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              fullWidth select label="Country" name="countryID"
              value={form.countryID} onChange={handleChange} size="small" required
            >
              {(countries || []).map((c) => (
                <MenuItem key={c.id} value={c.id}>{c.name}</MenuItem>
              ))}
            </TextField>
          </Grid>
        </Grid>
      </DialogContent>
      <DialogActions sx={{ px: 3, pb: 2 }}>
        <Button onClick={onClose} variant="outlined" disabled={saving}>Cancel</Button>
        <Button
          onClick={handleSave}
          variant="contained"
          disabled={saving || !form.name || !form.surname || !form.countryID}
          sx={{ background: '#e94560', '&:hover': { background: '#c73652' } }}
        >
          {saving ? 'Saving...' : isEdit ? 'Update' : 'Create'}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default HostDialog;
