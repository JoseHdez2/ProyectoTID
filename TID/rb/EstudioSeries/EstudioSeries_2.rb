
class EstudioSeries
  #
  # 2do paso: Cargar los generos en un array.
  #
  def cargarGeneros(carpetaGeneros, modo)

    # Colocamos las dir de los HTML en un array.
    dirsGeneros = Dir[carpetaGeneros+"/*.html"]

    # Ordenamos los archivos alfabéticamente.
    dirsGeneros.sort!

    # Por cada archivo,
    # crear un genero y agregarlo al array.
    count = 0
    dirsSeries.each do |dir|
      @series.push(Serie.new(dir, modo))
      count += 1
      puts "generos cargados: #{count}"
    end
  end
end
