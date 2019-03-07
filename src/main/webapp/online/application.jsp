<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
   <head>
      <meta charset="utf-8">
      <title>Camundanzia</title>
      <!-- Mobile Metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- Library CSS -->
      <link rel="stylesheet" href="css/bootstrap.css">
      <link rel="stylesheet" href="css/bootstrap-theme.css">
      <link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
      <link rel="stylesheet" href="css/animations.css" media="screen">
      <link rel="stylesheet" href="css/superfish.css" media="screen">
      <link rel="stylesheet" href="css/revolution-slider/css/settings.css" media="screen">
      <link rel="stylesheet" href="css/prettyPhoto.css" media="screen">
      <!-- Theme CSS -->
      <link rel="stylesheet" href="css/style.css">
      <link rel="stylesheet" href="css/global.css">
      <!-- Skin -->
      <link rel="stylesheet" href="css/colors/blue.css" class="colors">
      <!-- Responsive CSS -->
      <link rel="stylesheet" href="css/theme-responsive.css">

    <script src="camunda-bpm-sdk.js" ></script>
   </head>
   <body class="boxed home">
      <div class="page-mask">
            <div class="page-loader">

                <div class="spinner"></div>
                Loading...
            </div>

      </div>
      <div class="wrap">
         <!-- Header Start -->
         <header id="header">
<%@ include file="headerTopBar.inc" %>
<%@ include file="mainHeader.inc" %>
		</header>
         <!-- Content Start -->
         <div id="main">
            <!-- Title, Breadcrumb Start-->
            <div class="breadcrumb-wrapper">
               <div class="container">
                  <div class="row">
                     <div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
                        <h2 class="title">${param.lang == 'de' ? 'Antrag stellen' : 'Apply for Policy'}</h2>
                     </div>
                     <div class="col-lg-6 col-md-6 col-xs-12 col-sm-6">
                        <div class="breadcrumbs pull-right">
                           <ul>
				   <li>${param.lang == 'de' ? 'Sie sind hier:' : 'You are here:'}</li>
                              <li><a href="index.jsp?lang=${param.lang}">Home</a></li>
                              <li>${param.lang == 'de' ? 'Antrag stellen' : 'Apply for Policy'}</li>
                           </ul>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            
            <!-- Title, Breadcrumb End-->
            <!-- Main Content start-->
            <div class="content">
               <div class="container">
                  <div class="row">
                     <div class="col-lg-8 col-md-8 col-sm-6 col-xs-12" id="contact-form">
			     <h3 class="title">${param.lang == 'de' ? 'Kfz-Versicherung beantragen' : 'Car Insurance Application'}</h3>
                        <div class="divider"></div>
                        <div id="fieldsetForm">
                        <form method="post" class="reply" id="contact">
                           <fieldset>
                              <div class="row">
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <label>Name: <span>*</span></label>
                                    <input class="form-control" id="applicant" name="name" type="text" value="" required>
                                 </div>
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					 <label>${param.lang == 'de' ? 'Fahrzeug Hersteller' : 'Car Maker'}: <span>*</span></label>
                                    <select class="form-control" id="vehicleManufacturer">
                                       <option value="VW" selected>VW</option>
                                       <option value="BMW">BMW</option>
                                       <option value="Porsche">Porsche</option>
                                       <option value="Audi">Audi</option>
                                    </select>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					 <label>${param.lang == 'de' ? 'Geburtsdatum' : 'Date of Birth'}: <span>*</span></label>
                                    <input class="form-control" type="date" id="birthdate" name="birthdate" value="1980-01-01" required>
                                 </div>
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					 <label>${param.lang == 'de' ? 'Fahrzeug Typ' : 'Car Model'}: <span>*</span></label>
                                    <select class="form-control" id="vehicleType">
                                       <option value="Beatle">Beatle</option>
                                       <option value="Golf IV" selected>Golf IV</option>
                                       <option value="Golf V">Golf V</option>
                                       <option value="Passat">Passat</option>
                                    </select>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					 <label>${param.lang == 'de' ? 'E-Mail' : 'Email'}: <span>*</span></label>
                                    <input class="form-control" type="email" id="email" name="email" value="" required>
                                 </div>
                              </div>
                              <div class="row">
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					 <label>${param.lang == 'de' ? 'Geschlecht' : 'Gender'}: <span>*</span></label>
                                    <select class="form-control" id="selectSex" name="selectSex">
					    <option value="${param.lang == 'de' ? 'Frau' : 'female'}">${param.lang == 'de' ? 'weiblich' : 'female'}</option>
					    <option value="${param.lang == 'de' ? 'Mann' : 'male'}">${param.lang == 'de' ? 'männlich' : 'male'}</option>
                                    </select>
                                 </div>
                                 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					 <label>${param.lang == 'de' ? 'Unverbindliche Preisindikation' : 'Price Indication (non-binding)'}: </label>
                                    <input class="form-control" type="email" id="priceIndicationInCent" name="preisindikation" value="160,00 €" readonly>
                                 </div>
                                 
                              </div>

                           </fieldset>                           
			   <button id="triggerStartApplication" class="btn btn-normal btn-color submit" type="button">${param.lang == 'de' ? 'Antrag stellen' : 'Apply'}</button>
                        </form>
                           </div>
                           
			   <div id="applicationReceived" class="success alert-success alert" style="display:none">
			   		<p>${param.lang == 'de' ? 'Antrag erhalten - wir melden uns!' : 'Application received - we get in touch with you shortly!'}</p>
			   		<p>${param.lang == 'de' ? 'Ihre Antragsnummer lautet:' : 'Your application id is:'} <span id="applicationId"></span></p>
			   	</div>
                           <div class="clearfix">
                           </div>
                     </div>
                     <div class="col-lg-4 col-md-4 col-xs-12 col-sm-6">
                       <img src="http://polpix.sueddeutsche.com/bild/1.1196262.1376398621/900x600/kfzversicherung-versicherung-kfz-auto-assekuranz-tarif-tarifwechsel.jpg">
                     </div>
                  </div>
                  <div class="divider"></div>
               </div>
            </div>
            <!-- Main Content end-->
         </div>
         <!-- Content End -->
<%@ include file="footer.inc" %>
      </div>
      <!-- Wrap End -->
<%@ include file="scripts.inc" %>
   </body>
</html>
