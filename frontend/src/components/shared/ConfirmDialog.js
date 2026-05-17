import React from 'react';
import {
  Dialog, DialogTitle, DialogContent, DialogContentText,
  DialogActions, Button,
} from '@mui/material';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';

const ConfirmDialog = ({ open, title, message, onConfirm, onCancel }) => (
  <Dialog open={open} onClose={onCancel} maxWidth="xs" fullWidth>
    <DialogTitle sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
      <WarningAmberIcon color="warning" />
      {title || 'Confirm Delete'}
    </DialogTitle>
    <DialogContent>
      <DialogContentText>
        {message || 'Are you sure you want to delete this item? This action cannot be undone.'}
      </DialogContentText>
    </DialogContent>
    <DialogActions sx={{ px: 3, pb: 2 }}>
      <Button onClick={onCancel} variant="outlined">Cancel</Button>
      <Button onClick={onConfirm} variant="contained" color="error">Delete</Button>
    </DialogActions>
  </Dialog>
);

export default ConfirmDialog;
