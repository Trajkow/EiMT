import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { Box, Typography, Button } from '@mui/material';
import LockIcon from '@mui/icons-material/Lock';

const ProtectedRoute = ({ children, requireAdmin = false }) => {
  const { isAuthenticated, isAdmin } = useAuth();
  const location = useLocation();

  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  if (requireAdmin && !isAdmin) {
    return (
      <Box
        display="flex"
        flexDirection="column"
        alignItems="center"
        justifyContent="center"
        minHeight="60vh"
        gap={2}
      >
        <LockIcon sx={{ fontSize: 64, color: 'error.main' }} />
        <Typography variant="h5" fontWeight={700}>
          Access Denied
        </Typography>
        <Typography color="text.secondary">
          This action requires Administrator privileges.
        </Typography>
        <Button variant="contained" href="/">Go Home</Button>
      </Box>
    );
  }

  return children;
};

export default ProtectedRoute;
