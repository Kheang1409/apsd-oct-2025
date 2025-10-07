SELECT 
    l.Lease_Id,
    l.Lease_Number,
    l.StartDate,
    l.EndDate,
    l.MonthlyRentalRate,
    l.Tenant_Id,
    l.Apartment_Id,
    DATEDIFF(MONTH, l.StartDate, l.EndDate) + 1 AS Lease_Months,
    (DATEDIFF(MONTH, l.StartDate, l.EndDate) + 1) * l.MonthlyRentalRate AS revenue_earned
FROM Leases l
ORDER BY l.Lease_Id;
