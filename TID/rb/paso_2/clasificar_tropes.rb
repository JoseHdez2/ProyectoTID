

#
# Necesitamos: Carpeta con HTMLs de generos.
#
def clasificar_tropes_usando_generos(generos, hash = Hash.new { |h,k| h[k] = Hash.new(0) })
  countCollisions = 0
  countRedundant = 0
  generos.each do |genero|
    genero.tropes.each do |trope|
      if hash[trope] != hash.default  # Already filled
        if hash[trope] == genero.nombre
          puts "Redundant! Attempted => \"#{genero.nombre}\" on {#{trope} => #{hash[trope]}}"
          countRedundant += 1
        else
          puts "Collision! Attempted => \"#{genero.nombre}\" on {#{trope} => #{hash[trope]}}"
          countCollisions += 1
        end
      else
        hash[trope] = genero.nombre
      end
    end
  end
  puts "Collisions: #{countCollisions}, Redundant: #{countRedundant}"

  # Devolvemos hash con asociaciones { trope => genero }.
  return hash
end

#
# Necesitamos: CSV con clasificaciones de series.
#
def clasificar_tropes_usando_series(series, hash = Hash.new { |h,k| h[k] = Hash.new(0) })

  # hash: { trope => { genero => conteo } }
  # ...Es un hash anidado!!!

  series.each do |serie|
    serie.tropes.each do |trope|
      # Opcion 1: Ponemos a verdadero (Ignoramos multiples asignaciones)
      #hash[trope][serie.genero] = true;
      # Opcion 2: Incrementamos el contador (Contamos multiples asignaciones)
      hash[trope][serie.genero] = hash[trope][serie.genero] + 1
      # Opcion 3: Usar opcion 2 y luego decidir si ignorar las asignaciones o no.
    end
  end

  binding.pry
  return hash
end
