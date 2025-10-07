SELECT 
    Apartment_Id,
    Apartment_Number,
    Property_Name,
    FloorNo,
    Size,
    Number_Of_Rooms,
    Street,
    City,
    State,
    ZipCode
FROM Apartments
ORDER BY 
    Size DESC, 
    Apartment_Number ASC;
