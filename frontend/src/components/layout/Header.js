import React from 'react';
import { AppBar, Toolbar, Typography, Box, Button, Chip, Tooltip, IconButton } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import HomeIcon from '@mui/icons-material/Home';
import ApartmentIcon from '@mui/icons-material/Apartment';
import PeopleIcon from '@mui/icons-material/People';
import PublicIcon from '@mui/icons-material/Public';
import LogoutIcon from '@mui/icons-material/Logout';
import LoginIcon from '@mui/icons-material/Login';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';

const NAV_LINKS = [
  { to: '/', label: 'Home', icon: <HomeIcon fontSize="small" /> },
  { to: '/accommodations', label: 'Accommodations', icon: <ApartmentIcon fontSize="small" />, requireAuth: true },
  { to: '/hosts', label: 'Hosts', icon: <PeopleIcon fontSize="small" />, requireAuth: true },
  { to: '/countries', label: 'Countries', icon: <PublicIcon fontSize="small" />, requireAuth: true },
];

const Header = () => {
  const { isAuthenticated, isAdmin, user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <AppBar
      position="sticky"
      elevation={0}
      sx={{
        background: 'linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%)',
        borderBottom: '1px solid rgba(255,255,255,0.08)',
      }}
    >
      <Toolbar sx={{ gap: 1 }}>
        {/* Logo */}
        <ApartmentIcon sx={{ mr: 1, color: '#e94560' }} />
        <Typography
          variant="h6"
          component={Link}
          to="/"
          sx={{
            fontWeight: 800,
            color: 'white',
            textDecoration: 'none',
            letterSpacing: '-0.5px',
            flexShrink: 0,
            mr: 3,
          }}
        >
          StayFinder
        </Typography>

        {/* Nav links */}
        <Box sx={{ display: 'flex', gap: 0.5, flexGrow: 1 }}>
          {NAV_LINKS.filter(l => !l.requireAuth || isAuthenticated).map(({ to, label, icon }) => (
            <Button
              key={to}
              component={Link}
              to={to}
              startIcon={icon}
              sx={{
                color: 'rgba(255,255,255,0.85)',
                textTransform: 'none',
                fontWeight: 500,
                borderRadius: 2,
                px: 1.5,
                '&:hover': {
                  background: 'rgba(255,255,255,0.1)',
                  color: 'white',
                },
              }}
            >
              {label}
            </Button>
          ))}
        </Box>

        {/* Right side */}
        {isAuthenticated ? (
          <Box sx={{ display: 'flex', alignItems: 'center', gap: 1.5 }}>
            {isAdmin && (
              <Chip
                icon={<AdminPanelSettingsIcon />}
                label="Admin"
                size="small"
                sx={{
                  background: 'rgba(233,69,96,0.2)',
                  color: '#e94560',
                  border: '1px solid rgba(233,69,96,0.4)',
                  fontWeight: 600,
                }}
              />
            )}
            <Typography variant="body2" sx={{ color: 'rgba(255,255,255,0.7)' }}>
              {user?.username}
            </Typography>
            <Tooltip title="Logout">
              <IconButton
                onClick={handleLogout}
                size="small"
                sx={{
                  color: 'rgba(255,255,255,0.7)',
                  '&:hover': { color: '#e94560', background: 'rgba(233,69,96,0.1)' },
                }}
              >
                <LogoutIcon fontSize="small" />
              </IconButton>
            </Tooltip>
          </Box>
        ) : (
          <Box sx={{ display: 'flex', gap: 1 }}>
            <Button
              component={Link}
              to="/login"
              startIcon={<LoginIcon />}
              variant="outlined"
              size="small"
              sx={{
                color: 'white',
                borderColor: 'rgba(255,255,255,0.3)',
                textTransform: 'none',
                fontWeight: 600,
                borderRadius: 2,
                '&:hover': { borderColor: 'white', background: 'rgba(255,255,255,0.05)' },
              }}
            >
              Login
            </Button>
            <Button
              component={Link}
              to="/register"
              startIcon={<PersonAddIcon />}
              variant="contained"
              size="small"
              sx={{
                background: '#e94560',
                textTransform: 'none',
                fontWeight: 600,
                borderRadius: 2,
                '&:hover': { background: '#c73652' },
              }}
            >
              Register
            </Button>
          </Box>
        )}
      </Toolbar>
    </AppBar>
  );
};

export default Header;
