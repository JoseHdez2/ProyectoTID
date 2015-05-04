require 'csv'

# Crear una matriz desde un archivo CSV.
def mat_from_csv(path)
  arr_of_arrs = CSV.read(path)
  return arr_of_arrs
end

# Creamos un hash a partir de un CSV.
# Importante! Asumimos que el tamaño de fila es dos: [clave, valor]
def hash_from_csv(path, def_val = nil)
  # Creamos una matriz desde el archivo CSV.
  mat = mat_from_csv(path)

  # Creamos el hash que vamos a rellenar y devolver.
  hash = Hash.new(def_val)

  # Por cada fila, introducimos el par { fila[0] => fila[1] }.
  mat.each { |row| hash[row[0]] = row[1] }

  # Devolvemos el hash.
  return hash
end
