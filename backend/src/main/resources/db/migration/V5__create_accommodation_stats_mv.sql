CREATE MATERIALIZED VIEW accommodation_stats_mv AS
SELECT a.category AS category,
       COUNT(a.id) AS total_accommodations,
       SUM(a.num_rooms) AS total_rooms,
       AVG(a.num_rooms) AS avg_rooms
    FROM accommodation a
GROUP BY a.category;

CREATE UNIQUE INDEX accommodation_stats_mv_category_idx ON accommodation_stats_mv (category);
