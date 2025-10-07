SELECT 
    a.Apartment_Id,
    a.Apartment_Number,
    a.Property_Name,
    a.FloorNo,
    a.Size,
    a.Number_Of_Rooms,
    a.Street,
    a.City,
    a.State,
    a.ZipCode,
    l.Lease_Id,
    l.Lease_Number,
    l.StartDate,
    l.EndDate,
    l.MonthlyRentalRate,
    l.Tenant_Id
FROM Apartments a
LEFT JOIN Leases l
    ON a.Apartment_Id = l.Apartment_Id
ORDER BY a.Apartment_Number ASC;
