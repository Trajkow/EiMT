import React from 'react';
import {
  Box, Typography, Button, Grid, Card, CardContent, Container,
} from '@mui/material';
import { Link } from 'react-router-dom';
import ApartmentIcon from '@mui/icons-material/Apartment';
import PeopleIcon from '@mui/icons-material/People';
import PublicIcon from '@mui/icons-material/Public';
import { useAuth } from '../context/AuthContext';

const FEATURES = [
  {
    icon: <ApartmentIcon sx={{ fontSize: 48, color: '#e94560' }} />,
    title: 'Accommodations',
    desc: 'Browse, add, and manage all available accommodations across different categories.',
    to: '/accommodations',
  },
  {
    icon: <PeopleIcon sx={{ fontSize: 48, color: '#0f3460' }} />,
    title: 'Hosts',
    desc: 'Manage host information and their associated countries.',
    to: '/hosts',
  },
  {
    icon: <PublicIcon sx={{ fontSize: 48, color: '#533483' }} />,
    title: 'Countries',
    desc: 'Explore accommodations across different countries and continents.',
    to: '/countries',
  },
];

const HomePage = () => {
  const { isAuthenticated } = useAuth();

  return (
    <Box>
      {/* Hero */}
      <Box
        sx={{
          background: 'linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%)',
          borderRadius: 4,
          p: { xs: 4, md: 8 },
          mb: 6,
          color: 'white',
          textAlign: 'center',
          position: 'relative',
          overflow: 'hidden',
          '&::before': {
            content: '""',
            position: 'absolute',
            top: -100,
            right: -100,
            width: 400,
            height: 400,
            borderRadius: '50%',
            background: 'rgba(233,69,96,0.15)',
            pointerEvents: 'none',
          },
        }}
      >
        <Typography
          variant="h2"
          fontWeight={900}
          sx={{ letterSpacing: '-1px', mb: 2, position: 'relative' }}
        >
          Find Your Perfect{' '}
          <Box component="span" sx={{ color: '#e94560' }}>Stay</Box>
        </Typography>
        <Typography
          variant="h6"
          sx={{ color: 'rgba(255,255,255,0.7)', mb: 4, maxWidth: 560, mx: 'auto', fontWeight: 400 }}
        >
          Manage accommodations, hosts, and locations all in one place.
          Built for efficiency and elegance.
        </Typography>
        {!isAuthenticated ? (
          <Box sx={{ display: 'flex', gap: 2, justifyContent: 'center', flexWrap: 'wrap' }}>
            <Button
              component={Link} to="/register"
              variant="contained" size="large"
              sx={{
                background: '#e94560',
                fontWeight: 700,
                px: 4,
                borderRadius: 3,
                textTransform: 'none',
                fontSize: '1rem',
                '&:hover': { background: '#c73652' },
              }}
            >
              Get Started
            </Button>
            <Button
              component={Link} to="/login"
              variant="outlined" size="large"
              sx={{
                color: 'white',
                borderColor: 'rgba(255,255,255,0.4)',
                fontWeight: 700,
                px: 4,
                borderRadius: 3,
                textTransform: 'none',
                fontSize: '1rem',
                '&:hover': { borderColor: 'white', background: 'rgba(255,255,255,0.05)' },
              }}
            >
              Sign In
            </Button>
          </Box>
        ) : (
          <Button
            component={Link} to="/accommodations"
            variant="contained" size="large"
            sx={{
              background: '#e94560',
              fontWeight: 700,
              px: 4,
              borderRadius: 3,
              textTransform: 'none',
              fontSize: '1rem',
              '&:hover': { background: '#c73652' },
            }}
          >
            Browse Accommodations
          </Button>
        )}
      </Box>

      {/* Feature Cards */}
      <Container disableGutters>
        <Typography variant="h4" fontWeight={800} textAlign="center" mb={4} color="#1a1a2e">
          Everything You Need
        </Typography>
        <Grid container spacing={3}>
          {FEATURES.map(({ icon, title, desc, to }) => (
            <Grid item xs={12} md={4} key={title}>
              <Card
                component={isAuthenticated ? Link : 'div'}
                to={isAuthenticated ? to : undefined}
                sx={{
                  height: '100%',
                  borderRadius: 3,
                  textDecoration: 'none',
                  transition: 'all 0.2s',
                  border: '1px solid rgba(0,0,0,0.06)',
                  boxShadow: '0 2px 12px rgba(0,0,0,0.06)',
                  '&:hover': isAuthenticated ? {
                    transform: 'translateY(-4px)',
                    boxShadow: '0 12px 32px rgba(0,0,0,0.12)',
                  } : {},
                }}
              >
                <CardContent sx={{ p: 4, textAlign: 'center' }}>
                  {icon}
                  <Typography variant="h6" fontWeight={700} mt={2} mb={1} color="#1a1a2e">
                    {title}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    {desc}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>
    </Box>
  );
};

export default HomePage;
