import React, { useState } from 'react';
import {
    Dialog, DialogTitle, DialogContent, DialogActions,
    Button, TextField, MenuItem, Grid
} from '@mui/material';

const ReservationDialog = ({ open, onClose, onSave, accommodations }) => {
    const [form, setForm] = useState({
        accommodationId: '',
        reservedAt: '',
        releaseAt: ''
    });
    const [saving, setSaving] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    };

    const handleSave = async () => {
        setSaving(true);
        try {
            await onSave(form.accommodationId, {
                reservedAt: form.reservedAt,
                releaseAt: form.releaseAt
            });
            // reset form
            setForm({ accommodationId: '', reservedAt: '', releaseAt: '' });
            onClose();
        } finally {
            setSaving(false);
        }
    };

    return (
        <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
            <DialogTitle sx={{ fontWeight: 700, pb: 1 }}>
                Add New Reservation
            </DialogTitle>
            <DialogContent sx={{ pt: 2 }}>
                <Grid container spacing={2} sx={{ mt: 0.5 }}>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth select label="Accommodation" name="accommodationId"
                            value={form.accommodationId} onChange={handleChange} size="small" required
                        >
                            {(accommodations || []).map((a) => (
                                <MenuItem key={a.id} value={a.id}>
                                    {a.name}
                                </MenuItem>
                            ))}
                        </TextField>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth label="Reserved At" name="reservedAt" type="datetime-local"
                            value={form.reservedAt} onChange={handleChange} size="small" required
                            InputLabelProps={{ shrink: true }}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            fullWidth label="Release At" name="releaseAt" type="datetime-local"
                            value={form.releaseAt} onChange={handleChange} size="small" required
                            InputLabelProps={{ shrink: true }}
                        />
                    </Grid>
                </Grid>
            </DialogContent>
            <DialogActions sx={{ px: 3, pb: 2 }}>
                <Button onClick={onClose} variant="outlined" disabled={saving}>Cancel</Button>
                <Button
                    onClick={handleSave}
                    variant="contained"
                    disabled={saving || !form.accommodationId || !form.reservedAt || !form.releaseAt}
                    sx={{ background: '#e94560', '&:hover': { background: '#c73652' } }}
                >
                    {saving ? 'Saving...' : 'Book'}
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ReservationDialog;
