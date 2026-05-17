import React from 'react';
import { Box, Typography, Link, Divider } from '@mui/material';
import ApartmentIcon from '@mui/icons-material/Apartment';

const Footer = () => (
  <Box
    component="footer"
    sx={{
      mt: 'auto',
      py: 4,
      px: 3,
      background: 'linear-gradient(135deg, #1a1a2e 0%, #16213e 100%)',
      borderTop: '1px solid rgba(255,255,255,0.08)',
    }}
  >
    <Box
      sx={{
        maxWidth: 1200,
        mx: 'auto',
        display: 'flex',
        flexDirection: { xs: 'column', md: 'row' },
        justifyContent: 'space-between',
        alignItems: { xs: 'center', md: 'flex-start' },
        gap: 3,
      }}
    >
      <Box>
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 1, mb: 1 }}>
          <ApartmentIcon sx={{ color: '#e94560' }} />
          <Typography variant="h6" fontWeight={800} color="white">
            StayFinder
          </Typography>
        </Box>
        <Typography variant="body2" color="rgba(255,255,255,0.5)">
          Accommodation management platform
        </Typography>
      </Box>

      <Box sx={{ display: 'flex', gap: 3 }}>
        {[
          { label: 'Accommodations', href: '/accommodations' },
          { label: 'Hosts', href: '/hosts' },
          { label: 'Countries', href: '/countries' },
        ].map(({ label, href }) => (
          <Link
            key={href}
            href={href}
            underline="none"
            sx={{
              color: 'rgba(255,255,255,0.6)',
              fontSize: '0.875rem',
              fontWeight: 500,
              transition: 'color 0.2s',
              '&:hover': { color: '#e94560' },
            }}
          >
            {label}
          </Link>
        ))}
      </Box>
    </Box>

    <Divider sx={{ borderColor: 'rgba(255,255,255,0.08)', my: 3, maxWidth: 1200, mx: 'auto' }} />

    <Typography
      variant="caption"
      color="rgba(255,255,255,0.3)"
      display="block"
      textAlign="center"
    >
      © {new Date().getFullYear()} StayFinder — FINKI EIMT Labs
    </Typography>
  </Box>
);

export default Footer;
