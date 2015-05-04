
# Se da la opcion a pasar un Hash, para combinar metodos de clasificacion.

# Usamos generos, que contienen tropes.
# Obtenemos hash con asociaciones { trope => genero }.
def clasificar_tropes_usando_generos(generos, hash = Hash.new(GENERO_DESCONOCIDO))
  generos.each do |genero|
    genero.tropes.each do |trope|
      if hash[trope] != hash.default
        puts "Collision! Attempted => \"#{genero.nombre}\" on {#{trope} => #{hash[trope]}}; }"
      else
        hash[trope] = genero.nombre
      end
    end
  end

  # Devolvemos hash con asociaciones { trope => genero }.
  return hash
end
