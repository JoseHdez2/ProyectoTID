
def mat_from_csv(path)
  arr_of_arrs = CSV.read(path)
  return arr_of_arrs
end

def hash_from_csv(path)
  mat = mat_from_csv(path)

end
