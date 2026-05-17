import React, { useState, useEffect } from 'react';
import {
  Dialog, DialogTitle, DialogContent, DialogActions,
  Button, TextField, MenuItem, Grid,
} from '@mui/material';

const CONTINENTS = [
  'Africa', 'Antarctica', 'Asia', 'Australia/Oceania',
  'Europe', 'North America', 'South America',
];

const EMPTY = { name: '', continent: '' };

const CountryDialog = ({ open, onClose, onSave, country }) => {
  const isEdit = !!country;
  const [form, setForm] = useState(EMPTY);
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    if (country) {
      setForm({ name: country.name || '', continent: country.continent || '' });
    } else {
      setForm(EMPTY);
    }
  }, [country, open]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    setSaving(true);
    try {
      await onSave(form);
      onClose();
    } finally {
      setSaving(false);
    }
  };

  return (
    <Dialog open={open} onClose={onClose} maxWidth="xs" fullWidth>
      <DialogTitle sx={{ fontWeight: 700, pb: 1 }}>
        {isEdit ? 'Edit Country' : 'Add New Country'}
      </DialogTitle>
      <DialogContent sx={{ pt: 2 }}>
        <Grid container spacing={2} sx={{ mt: 0.5 }}>
          <Grid item xs={12}>
            <TextField
              fullWidth label="Country Name" name="name" value={form.name}
              onChange={handleChange} required size="small"
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              fullWidth select label="Continent" name="continent"
              value={form.continent} onChange={handleChange} size="small" required
            >
              {CONTINENTS.map((c) => (
                <MenuItem key={c} value={c}>{c}</MenuItem>
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
          disabled={saving || !form.name || !form.continent}
          sx={{ background: '#e94560', '&:hover': { background: '#c73652' } }}
        >
          {saving ? 'Saving...' : isEdit ? 'Update' : 'Create'}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default CountryDialog;
