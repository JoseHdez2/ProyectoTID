def clases_series_a_generos(clases_series, series, generos = {})

  series.each do |nombre, tropes|
    # Cargamos los tropes de la serie actual
    tropes_serie = tropes
    # ...Y tambien el genero asignado a la serie actual
    genero_serie = clases_series[nombre]

    # Obtenemos los tropes del genero al que vamos a agregar
    tropes_genero = generos[genero_serie]
    # Cada trope de la serie va a parar al genero que estamos editando
    binding.pry
    tropes_serie.each { |trope| tropes_genero.push(trope) }
    # Volvemos a guardar el array
    generos[genero_serie] = tropes_genero
  end

  return generos
end
